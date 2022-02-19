package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final int DEFAULT_TIME_OUT = 5;
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_VALUE = "https://demo.opencart.com/index.php?route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";

	public static final List<String> EXPECTED_ACCOUNTS_SECTION_LIST = new ArrayList<>(
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"));
	public static final int PRODUCT_IMAGE_COUNT = 4;
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";

}
