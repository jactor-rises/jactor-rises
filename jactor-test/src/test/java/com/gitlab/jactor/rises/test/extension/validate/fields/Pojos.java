package com.gitlab.jactor.rises.test.extension.validate.fields;

class Pojos {
    static class ObjPojo {
        private Object objField;

        Object getObjField() {
            return objField;
        }

        @SuppressWarnings("unused") // used by reflection
        public void setObjField(Object objField) {
            this.objField = objField;
        }
    }

    static class StrPojo {
        private String strField;

        String getStrField() {
            return strField;
        }

        @SuppressWarnings("unused") // used by reflection
        public void setStrField(String strField) {
            this.strField = strField;
        }
    }
}
