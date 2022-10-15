package com.sp.lib.poc.regex;

import java.util.regex.Pattern;

/**
 * [Add comments here]
 *
 * @author luchao Created in 11:38
 */
public class RegexEnhancePoc {
    private final static String PATTERN = "(.)*+ok";

    public static void main(String[] args) {
        String fullUrl = "https://blog.csdn.net/weixin_33378701/article/details/112070257?utm_medium=distribute.pc_relevant." +
                "none-task-blog-2~default~baidujs_baidulandingword~default-8-112070257-blog-111185009.pc_relevant_multi_platform_" +
                "whitelistv3&spm=1001.2101.3001.4242.5&utm_relevant_index=11&ck=_ga=GA1.2.1501757572.1660730157; _e=2; " +
                "_za_sso_session_=j%3A%7B%22adAccount%22%3A%22luchao%22%2C%22companyId%22%3A%2210022%22%2C%22companyName%22%3A%22%E" +
                "4%BC%97%E5%AE%89%E5%9C%A8%E7%BA%BF%E8%B4%A2%E4%BA%A7%E4%BF%9D%E9%99%A9%E8%82%A1%E4%BB%BD%E6%9C%89%E9%99%90%E5%85%AC%E" +
                "5%8F%B8%22%2C%22createdTime%22%3A1650211200000%2C%22custId%22%3A16759276%2C%22custNo%22%3A%2216352%22%2C%22departName%22" +
                "%3A%22%E6%9E%B6%E6%9E%84%E7%BB%84%22%2C%22departmentId%22%3A1538012%2C%22departmentName%22%3A%22%E6%9E%B6%E6%9E%84%E7%BB%" +
                "84%22%2C%22deptTreeCode%22%3A%2210022%5E1538001%5E1538002%5E1538007%22%2C%22email%22%3A%22luchao%40zhongan.com%22%2C%22gran" +
                "tOrgs%22%3A%5B%5D%2C%22id%22%3A6650013%2C%22name%22%3A%22%E9%99%86%E8%B6%85%22%2C%22no%22%3A%22wz225234%22%2C%22phone%22%3A%2213" +
                "916841702%22%2C%22relateCompanyId%22%3A10022%2C%22relateCompanyName%22%3A%22%E4%BC%97%E5%AE%89%E5%9C%A8%E7%BA%BF%E8%B4%A2%E4%BA%A7%E4%BF%9D%E9%9" +
                "9%A9%E8%82%A1%E4%BB%BD%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%22%2C%22sex%22%3A%22%E7%94%B7%22%2C%22sid%22%3A%22c041cb107ada440eaf2145ce4934eb92%22%2C%22t" +
                "opDepartmentId%22%3A0%2C%22username%22%3A%22luchao%22%2C%22wechat%22%3A%2213916841702%22%2C%22zaWorkType%22%3A%22zs%22%7D; za_sky_ops=luchao";
        System.out.println("Test str length:" + fullUrl.length());

        Pattern pattern = Pattern.compile(PATTERN);

        long start = System.currentTimeMillis();
        pattern.matcher(fullUrl).matches();

        long end = System.currentTimeMillis();
        System.out.println("Match cost time:" + (end - start));
    }
}
