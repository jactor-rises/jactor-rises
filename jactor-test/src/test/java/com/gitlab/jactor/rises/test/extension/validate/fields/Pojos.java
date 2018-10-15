package com.gitlab.jactor.rises.test.extension.validate.fields;

@SuppressWarnings("WeakerAccess") // public for reflection...
public class Pojos {
    public static class ObjPojo {
        private Object objField;

        Object getObjField() {
            return objField;
        }

        public void setObjField(Object objField) { // public for reflection...
            this.objField = objField;
        }
    }

    public static class StrPojo {
        private String strField;

        String getStrField() {
            return strField;
        }

        @SuppressWarnings("unused") public void setStrField(String strField) { // used by reflection
            this.strField = strField;
        }
    }
}
