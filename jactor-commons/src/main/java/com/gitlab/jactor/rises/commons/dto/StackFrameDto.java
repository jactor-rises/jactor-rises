package com.gitlab.jactor.rises.commons.dto;

import java.io.Serializable;

public class StackFrameDto implements Serializable {
    private String className;
    private String fileName;
    private String methodName;
    private int lineNumber;

    @Override public String toString() {
        return String.format("%s.%s(%s:%d)", getClassName(), getMethodName(), getFileName(), getLineNumber());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public static StackFrameDto from(StackWalker.StackFrame stackFrame) {
        StackFrameDto stackFrameDto = new StackFrameDto();
        stackFrameDto.setClassName(stackFrame.getClassName());
        stackFrameDto.setFileName(stackFrame.getFileName());
        stackFrameDto.setLineNumber(stackFrame.getLineNumber());
        stackFrameDto.setMethodName(stackFrame.getMethodName());

        return stackFrameDto;
    }
}
