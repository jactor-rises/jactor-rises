package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.AbstractBuilder;

class ObjPojoBuilder extends AbstractBuilder<Pojos.ObjPojo> {
    private Pojos.ObjPojo objPojo = new Pojos.ObjPojo();

    ObjPojoBuilder() {
        super(RequiredFieldsExtensionTest::validateObjPojoWithRequiredField);
    }

    @Override protected Pojos.ObjPojo buildBean() {
        return objPojo;
    }

    public ObjPojoBuilder with(Object obj) {
        objPojo.setObjField(obj);
        return this;
    }
}
