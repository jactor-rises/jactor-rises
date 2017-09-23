INSERT INTO T_USER (USER_NAME, EMAIL) VALUES ('jactor', 'tor.egil.jacobsen@gmail.com');
INSERT INTO T_USER (USER_NAME, EMAIL) VALUES ('tip', 'suthatip.jacobsen@gmail.com');

INSERT INTO T_PERSON (DESCRIPTION) VALUES ('jactor.desc');
INSERT INTO T_PERSON (DESCRIPTION) VALUES ('tip.desc');

UPDATE T_USER
SET PERSON_ID = (
  SELECT ID
  FROM T_PERSON
  WHERE DESCRIPTION = 'jactor.desc'
)
WHERE USER_NAME = 'jactor';

UPDATE T_USER
SET PERSON_ID = (
  SELECT ID
  FROM T_PERSON
  WHERE DESCRIPTION = 'tip.desc'
)
WHERE USER_NAME = 'tip';