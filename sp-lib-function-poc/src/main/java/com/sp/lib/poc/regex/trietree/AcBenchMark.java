package com.sp.lib.poc.regex.trietree;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * AC Benchmark测试
 *
 * @author luchao Created in 16:45
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Threads(100)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class AcBenchMark {
    static String shortUrl = "https://blog.csdn.net/weixin_33378701/health/api-docs./metric";

    static String longUrl = "https://blog.csdn.net/weixin_33378701/health/details/112070257?utm_medium=distribute.pc_relevant." +
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

    Trie trie = Trie.builder()
            .ignoreOverlaps()
//            .onlyWholeWords()
            .addKeyword("/health")
            .addKeyword("ok")
            .addKeyword("/api-docs.")
            .addKeyword("/actuator/health")
            .addKeyword("/prometheus")
            .addKeyword("/swagger.")
            .addKeyword(".png")
            .addKeyword(".css")
            .addKeyword(".js")
            .addKeyword(".html")
            .addKeyword("/favicon.ico")
            .addKeyword("/hystrix.stream")
            .addKeyword("/nacos/v1/")
            .addKeyword("/nereus/alpha/")
            .addKeyword("/diamond-server/")
            .addKeyword("/notifications")
            .addKeyword("/health/")
            .addKeyword("/metric?startTime/")
            .build();


    @Benchmark
    public void testAcMatch(){
        Collection<Emit> emits = trie.parseText(longUrl);
//        emits.forEach(t -> {
//            System.out.println(t);
//        });
    }

    List<Pattern> patterns = buildPatterns();

    public List<Pattern> buildPatterns() {
        List<Pattern> patterns = new ArrayList<>();
//        patterns.add(Pattern.compile("/health"));
//        patterns.add(Pattern.compile("(.)*ok"));
//        patterns.add(Pattern.compile("/api-docs.*"));
//        patterns.add(Pattern.compile("/actuator/health"));
//        patterns.add(Pattern.compile("/prometheus"));
//        patterns.add(Pattern.compile("/swagger.*"));
//        patterns.add(Pattern.compile(".*.png"));
//        patterns.add(Pattern.compile(".*.css"));
//        patterns.add(Pattern.compile("/hystrix.stream"));
//        patterns.add(Pattern.compile("^(.)*/nacos/v1/(.)*$"));
//        patterns.add(Pattern.compile("^(.)*/nacos/v1/(.)*$"));
//        patterns.add(Pattern.compile("^(.)*/nereus/alpha/(.)*$"));
//        patterns.add(Pattern.compile("^(.)*/diamond-server/(.)*$"));
//        patterns.add(Pattern.compile("^(.)*/notifications$"));
//        patterns.add(Pattern.compile("^(.)*/health/(.)*$"));
//        patterns.add(Pattern.compile("^(.)*/metric?startTime/(.)*$"));
        patterns.add(Pattern.compile("/health"));
        patterns.add(Pattern.compile("ok$"));
        patterns.add(Pattern.compile("^/api-docs"));
        patterns.add(Pattern.compile("/actuator/health"));
        patterns.add(Pattern.compile("/prometheus"));
        patterns.add(Pattern.compile("^/swagger"));
        patterns.add(Pattern.compile("^.js"));
        patterns.add(Pattern.compile("^.html"));
        patterns.add(Pattern.compile("/favicon.ico"));
        patterns.add(Pattern.compile(".png$"));
        patterns.add(Pattern.compile(".css$"));
        patterns.add(Pattern.compile("/hystrix.stream"));
        patterns.add(Pattern.compile("/nacos/v1/"));
        patterns.add(Pattern.compile("/nereus/alpha/"));
        patterns.add(Pattern.compile("/diamond-server/"));
        patterns.add(Pattern.compile("/notifications$"));
        patterns.add(Pattern.compile("/health/"));
        patterns.add(Pattern.compile("^/metric?startTime"));

        return patterns;
    }

    @Benchmark
    public void testRegexMatch(){
        boolean matchSuccess = false;
        for (Pattern pattern : patterns) {
            if (pattern.matcher(longUrl).matches()){
                matchSuccess = true;
                break;
            }
        }
    }

}
