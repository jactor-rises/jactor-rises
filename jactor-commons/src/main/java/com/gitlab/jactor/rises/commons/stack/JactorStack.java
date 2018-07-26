package com.gitlab.jactor.rises.commons.stack;

import com.gitlab.jactor.rises.commons.dto.StackFrameDto;

import java.util.List;
import java.util.stream.Collectors;

public class JactorStack {

    private static final int INDEX_OF_COMMONS_PACKAGE = JactorStack.class.getPackageName().indexOf(".commons");
    private static final String PACKAGE_JACTOR_RISES = JactorStack.class.getPackageName().substring(0, INDEX_OF_COMMONS_PACKAGE);

    public List<StackWalker.StackFrame> fetch() {
        return fetchFrames();
    }

    public List<StackFrameDto> fetchAsDtos() {
        return fetchFrames().stream()
                .map(StackFrameDto::from)
                .collect(Collectors.toList());
    }

    private List<StackWalker.StackFrame> fetchFrames() {
        return StackWalker.getInstance()
                .walk(frame -> frame
                        .filter(stackFrame -> stackFrame.getClassName().startsWith(PACKAGE_JACTOR_RISES))
                        .skip(3) // do not fetch this frame, the public fetch frame  or the caller frame...
                        .collect(Collectors.toList())
                );
    }
}
