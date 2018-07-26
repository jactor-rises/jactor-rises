package com.gitlab.jactor.rises.commons.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ServerErrorDto {
    private List<StackFrameDto> errorFrames;
    private String exceptionClass;
    private String exceptionMessage;

    public List<StackFrameDto> getErrorFrames() {
        return errorFrames;
    }

    public void setErrorFrames(List<StackFrameDto> errorFrames) {
        this.errorFrames = errorFrames;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public static ServerErrorDto from(RuntimeException runtimeException, List<StackFrameDto> errorFrames) {
        ServerErrorDto serverErrorDto = new ServerErrorDto();

        serverErrorDto.setErrorFrames(errorFrames);
        serverErrorDto.setExceptionClass(runtimeException.getClass().getName());
        serverErrorDto.setExceptionMessage(runtimeException.getMessage());

        return serverErrorDto;
    }
}
