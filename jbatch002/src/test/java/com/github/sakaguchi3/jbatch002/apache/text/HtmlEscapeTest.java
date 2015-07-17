package com.github.sakaguchi3.jbatch002.apache.text;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

public class HtmlEscapeTest {

	@Test
	public void escape01Test() {
		System.out.println("01------------------");
		var arr = List.of( //
				StringEscapeUtils.escapeCsv("a\",b[\\b,'\""), //
				StringEscapeUtils.escapeEcmaScript("<script>alert('XSS');</script>"), //
				StringEscapeUtils.escapeJava("\"java's\"Hello!t#$%&'"), //
				StringEscapeUtils.escapeJson("{response:{text:'He didn't say, \"Stop!\"',body:{el:$('.responseXHR'),html:'<div class=\"message\"'>success</div>'}}}"), //
				StringEscapeUtils.escapeXml10("<script>alert('XSS');</script>"), //
				StringEscapeUtils.escapeXml11("<script>alert('XSS');</script>"), //
				StringEscapeUtils.escapeHtml4("<script>alert('XSS');</script>") //
		);
		arr.forEach(str -> System.out.println(str));
	}

	@Test
	public void escape02Test() {
		System.out.println("02------------------");
		var source = "<a href=\"./index.php?foo=bar&hoge=hoge\">'ココ'からジャンプ</a>";
		Function<String, String> f = s -> StringEscapeUtils.escapeHtml4(s);

		var arr = List.of( //
				f.apply(" "), //
				f.apply("  "), //
				f.apply("   "), //
				f.apply("&nbsp;"), //
				f.apply("&nbsp;&nbsp;"), //
				source, //
				StringEscapeUtils.escapeHtml4(source) //
		);
		arr.forEach(str -> System.out.println(str));
	}

	private void debug() {

	}

}
