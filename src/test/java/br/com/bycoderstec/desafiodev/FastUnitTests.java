package br.com.bycoderstec.desafiodev;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"br.com.bycoderstec.desafiodev.service", "br.com.bycoderstec.desafiodev.web.rest"})
public class FastUnitTests {
}
