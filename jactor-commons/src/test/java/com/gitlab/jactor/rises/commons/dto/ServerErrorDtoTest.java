package com.gitlab.jactor.rises.commons.dto;

import com.gitlab.jactor.rises.commons.stack.JactorStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A ServerErrorDto")
class ServerErrorDtoTest {

    @DisplayName("should give information about stack and exception")
    @Test void shouldGiveInformationAboutStackAndException() {
        List<StackFrameDto> stackFrames = fetchFrames();
        ServerErrorDto serverErrorDto = ServerErrorDto.from(new IllegalArgumentException("test exception"), stackFrames);

        assertAll(
                () -> assertThat(serverErrorDto.getExceptionClass()).as("exception").isEqualTo(IllegalArgumentException.class.getName()),
                () -> assertThat(serverErrorDto.getExceptionMessage()).as("message").isEqualTo("test exception"),
                () -> assertThat(serverErrorDto.getErrorFrames().get(0).getClassName()).as("class name from first frame")
                        .isEqualTo("com.gitlab.jactor.rises.commons.dto.ServerErrorDtoTest"),
                () -> assertThat(serverErrorDto.getErrorFrames().get(0).getMethodName()).as("method name from first frame")
                        .isEqualTo("shouldGiveInformationAboutStackAndException"),
                () -> assertThat(serverErrorDto.getErrorFrames().get(0).getFileName()).as("file name from first frame")
                        .isEqualTo("ServerErrorDtoTest.java")
        );

    }

    private List<StackFrameDto> fetchFrames() {
        return new JactorStack().fetchAsDtos();
    }
}