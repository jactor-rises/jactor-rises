package com.gitlab.jactor.rises.commons.stack;

import com.gitlab.jactor.rises.commons.dto.StackFrameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("A StackResolver")
class StackResolverTest {

    private static final IllegalStateException ILLEGAL_STATE_EXCEPTION = new IllegalStateException("test exception");

    @DisplayName("should produce stack frames only consisting of jactor.rises elements")
    @Test void shouldProduceStackFrames() {
        List<StackFrameDto> stack = new Service().simulateMethodCall();

        assertAll(
                () -> assertThat(stack).as("stack").size().isEqualTo(2),
                () -> assertThat(stack.get(0).asString()).as("first frame").contains("com.gitlab.jactor.rises.commons.stack.StackResolverTest$Service.simulateMethodCall(StackResolverTest.java:"),
                () -> assertThat(stack.get(1).asString()).as("second frame").contains("com.gitlab.jactor.rises.commons.stack.StackResolverTest.shouldProduceStackFrames(StackResolverTest.java:")
        );
    }

    @DisplayName("should log the stack")
    @Test void shouldLogStack() {
        Service service = new Service();

        StackResolver.MsgLog msgLogMock = service.stackReader.msgLogMock;
        StackResolver.ThrowableLog throwableLogMock = service.stackReader.throwableLogMock;
        service.simulateExceptionInMethod();

        assertAll(
                () -> verify(msgLogMock).log("A(n) IllegalStateException occurred during execution of code in jactor.rises:"),
                () -> verify(msgLogMock).log(contains("    com.gitlab.jactor.rises.commons.stack.StackResolverTest$Service.simulateExceptionInMethod(StackResolverTest.java:")),
                () -> verify(msgLogMock).log(contains("    com.gitlab.jactor.rises.commons.stack.StackResolverTest.shouldLogStack(StackResolverTest.java:")),
                () -> verify(throwableLogMock).log("Full stack trace", ILLEGAL_STATE_EXCEPTION)
        );
    }


    private class Service {
        private final StackReader stackReader = new StackReader();

        List<StackFrameDto> simulateMethodCall() {
            return stackReader.readStackWithoutCallerOfMethod();
        }

        void simulateExceptionInMethod() {
            stackReader.logStachWithoutThisMethodCausedBy(ILLEGAL_STATE_EXCEPTION);
        }
    }

    private class StackReader {
        StackResolver.MsgLog msgLogMock = mock(StackResolver.MsgLog.class);
        StackResolver.ThrowableLog throwableLogMock = mock(StackResolver.ThrowableLog.class);

        List<StackFrameDto> readStackWithoutCallerOfMethod() {
            return StackResolver.STACK_RESOLVER.fetch(2);
        }

        void logStachWithoutThisMethodCausedBy(IllegalStateException exception) {
            StackResolver.logStack(msgLogMock, throwableLogMock, exception);
        }
    }
}