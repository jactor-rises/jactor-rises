package com.github.jactor.rises.commons.stack;

import com.github.jactor.rises.commons.dto.StackFrameDto;

import java.util.List;
import java.util.stream.Collectors;

public class StackResolver {

    private static final int INDEX_OF_COMMONS_PACKAGE = StackResolver.class.getPackageName().indexOf(".commons");
    private static final String PACKAGE_JACTOR_RISES = StackResolver.class.getPackageName().substring(0, INDEX_OF_COMMONS_PACKAGE);
    static final StackResolver STACK_RESOLVER = new StackResolver();

    private StackResolver() {
        // remove public access of the constructor
    }

    private void log(MsgLog msgLog, ThrowableLog throwableLog, Throwable throwable) {
        String nameOfThrowable = throwable.getClass().getSimpleName();
        msgLog.log(String.format("A(n) %s occurred during execution of code in jactor.rises:", nameOfThrowable));

        List<StackFrameDto> stackFrames = fetch(3); // skip this frame, the fetch frame, and the caller of this method
        stackFrames.forEach(stack -> msgLog.log(String.format("    %s", stack.asString())));

        throwableLog.log("Full stack trace", throwable);
    }

    List<StackFrameDto> fetch(int skipFrames) {
        return StackWalker.getInstance()
                .walk(frame -> frame
                        .filter(stackFrame -> stackFrame.getClassName().startsWith(PACKAGE_JACTOR_RISES))
                        .skip(skipFrames)
                        .collect(Collectors.toList())
                )
                .stream()
                .map(StackFrameDto::from)
                .collect(Collectors.toList());
    }

    public static void logStack(MsgLog msgLog, ThrowableLog throwableLog, Throwable throwable) {
        STACK_RESOLVER.log(msgLog, throwableLog, throwable);
    }

    @FunctionalInterface
    public interface MsgLog {
        void log(String message);
    }

    @FunctionalInterface
    public interface ThrowableLog {
        void log(String message, Throwable throwable);
    }
}
