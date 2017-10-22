package org.tarena.note.service.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)//批量调用多个Test类
@SuiteClasses({JUnitTestDemo.class,TestUserService.class})
public class TestSuilt {

}
