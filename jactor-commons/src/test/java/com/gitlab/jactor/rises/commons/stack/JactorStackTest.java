package com.gitlab.jactor.rises.commons.stack;

import com.gitlab.jactor.rises.commons.dto.StackFrameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A JactorStack")
class JactorStackTest {

    @DisplayName("should produce stack frames only consisting of jactor.rises elements")
    @Test void shouldProduceStackFrames() {
        List<StackWalker.StackFrame> jactorStack = new Service().simulateMethodCall(new JactorStack());
        jactorStack.forEach(System.out::println);

        assertAll(
                () -> assertThat(jactorStack).as("jactorStack").size().isEqualTo(2),
                () -> assertThat(jactorStack.get(0).getMethodName()).as("first frame").isEqualTo("simulateMethodCall"),
                () -> assertThat(jactorStack.get(1).getMethodName()).as("second frame").isEqualTo("shouldProduceStackFrames")
        );
    }

    @DisplayName("should produce stack frames as dtos")
    @Test void shouldProduceStackFramesAsDtos() {
        List<StackFrameDto> jactorStack = new Service().simulateMethodCallAndReturnStackAsDtos(new JactorStack());
        jactorStack.forEach(System.out::println);

        assertAll(
                () -> assertThat(jactorStack).as("jactorStack").size().isEqualTo(2),
                () -> assertThat(jactorStack.get(0).toString()).as("first frame").contains("JactorStackTest$Service.simulateMethodCallAndReturnStackAsDtos(JactorStackTest.java:"),
                () -> assertThat(jactorStack.get(1).toString()).as("second frame").contains("JactorStackTest.shouldProduceStackFramesAsDtos(JactorStackTest.java:")
        );
    }

    private class Service {
        private final StackCall stackCall = new StackCall();

        List<StackWalker.StackFrame> simulateMethodCall(JactorStack jactorStack) {
            return stackCall.readStackWithoutCallerOfMethod(jactorStack);
        }

        List<StackFrameDto> simulateMethodCallAndReturnStackAsDtos(JactorStack jactorStack) {
            return stackCall.readStackWithoutCallerOfMethodAndReturnDtos(jactorStack);
        }
    }

    private class StackCall {
        List<StackWalker.StackFrame> readStackWithoutCallerOfMethod(JactorStack jactorStack) {
            return jactorStack.fetch();
        }

        List<StackFrameDto> readStackWithoutCallerOfMethodAndReturnDtos(JactorStack jactorStack) {
            return jactorStack.fetchAsDtos();
        }
    }
}