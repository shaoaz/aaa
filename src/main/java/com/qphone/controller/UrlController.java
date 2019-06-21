package com.qphone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UrlController {
	@RequestMapping("/{url}")
	public String aa(@PathVariable("url") String url) {

		return url;
	}
}
