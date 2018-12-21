package com.viki.javaplus.bloomfilter;

public class MybloomFilterTest {
        private final String[] URLS = {
                "http://www.csdn.net/",
                "http://www.baidu.com/",
                "http://www.google.com.hk",
                "http://www.cnblogs.com/",
                "http://www.zhihu.com/",
                "https://www.shiyanlou.com/",
                "http://www.google.com.hk",
                "https://www.shiyanlou.com/",
                "http://www.csdn.net/"
        };

        private void testBloomFilter() {
            MyBloomFilter filter = new MyBloomFilter();
            for (int i = 0; i < URLS.length; i++) {
                if (filter.contains(URLS[i])) {
                    System.out.println("contain: " + URLS[i]);
                    continue;
                }

                filter.add(URLS[i]);
            }
        }

        public static void main(String[] args) {
            MybloomFilterTest t = new MybloomFilterTest();
            t.testBloomFilter();
        }


}
