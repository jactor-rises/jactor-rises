package com.gitlab.jactor.rises.commons.dto;

public class StackFrameDto {

    private final String className;
    private final String fileName;
    private final String methodName;
    private int lineNumber;

    private StackFrameDto(String className, String fileName, int lineNumber, String methodName) {
        this.className = className;
        this.fileName = fileName;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    public String asString() {
        return String.format("%s.%s(%s:%d)", className, methodName, fileName, lineNumber);
    }

    @Override public String toString() {
        return asString();
    }

    public static StackFrameDto from(StackWalker.StackFrame stackFrame) {
        return new StackFrameDto(
                stackFrame.getClassName(),
                stackFrame.getFileName(),
                stackFrame.getLineNumber(),
                stackFrame.getMethodName()
        );
    }
}
