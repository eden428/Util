package com.eden.common.util.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author Zero
 *         Created on 2015/2/26.
 */
public class WordCountUtils {

    public static boolean isNumeric(String text) {
        return false;
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
        }
    }

    private static final Pattern tagPattern = Pattern.compile("\\{/?\\d+/?\\}");

    public static String clearTags(String text) {
        if (text == null) return null;
        if (text.contains("{") && text.contains("}")) {
            return tagPattern.matcher(text).replaceAll("");
        }
        return text;
    }

    public static long getWordCount(String segment) {
        return getWordCount(segment, true);
    }

    public static long getWordCount(String segment, boolean clearTags) {
        if (segment == null || segment.trim().isEmpty()) {
            return 0;
        }
        if (segment.length() == 1) {
            return 1;
        }
        if (clearTags) {
            segment = clearTags(segment);
        }
        long count = 0;
        StringBuilder word = new StringBuilder();
        int i = 0;
        for (int len = segment.length(); i < len; i++) {
            char c = segment.charAt(i);
            if (isCJKChar(c)) {//如果是象形文字,直接加一
                if (word.length() > 0) {
                    count++;
                    word.setLength(0);
                }
                count++;
            } else if (Character.isWhitespace(c)) {//如果是空白字符
                if (word.length() > 0) {
                    count++;
                    word.setLength(0);
                }
            } else {//英文和标点符号,标点符号是归在单词里一起算的
                word.append(c);
                if (i == len - 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static long getWordCount(Collection<String> c) {
        long count = 0;
        for (String s : c) {
            count += getWordCount(s);
        }
        return count;
    }

    /**
     * 是否是象形文字 中文,日文,韩文...
     * Whether is the hieroglyphics, Chinese, Japanese, korean...
     *
     * @param c
     * @return
     */
    public static boolean isCJKChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) ||
                (ub == Character.UnicodeBlock.CJK_COMPATIBILITY) ||
                (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) ||
                (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) ||
                (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) ||
                (ub == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT) ||
                (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) ||
                (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) ||
                (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) ||
                (ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) ||
                (ub == Character.UnicodeBlock.HANGUL_JAMO) ||
                (ub == Character.UnicodeBlock.HANGUL_SYLLABLES) ||
                (ub == Character.UnicodeBlock.HIRAGANA) ||
                (ub == Character.UnicodeBlock.KATAKANA) ||
                (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)) {
            return true;
        }
        return false;
    }


    public static Result count(String text) {
        if (text == null || text.trim().isEmpty()) return Result.EMPTY;
        Result result = new Result();
        result.wordCount = getWordCount(text);
        result.charCount = text.length();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isCJKChar(c)) {
                result.cjkCharCount += 1;
            }
        }
        return result;
    }

    public static class Result {

        public static final Result EMPTY = new Result();

        protected long wordCount;

        protected boolean error;

        protected long charCount;

        protected long cjkCharCount;


        public Result() {
        }

        public static Result createErrorResult() {
            Result result = new Result();
            result.setError(true);
            return result;
        }

        public long getWordCount() {
            return wordCount;
        }

        public Result add(Result result) {
            this.wordCount += result.wordCount;
            this.cjkCharCount += result.cjkCharCount;
            this.charCount += result.charCount;
            return this;
        }

        public boolean hasError() {
            return error;
        }

        public Result setError(boolean error) {
            this.error = error;
            return this;
        }

        public long getCharCount() {
            return charCount;
        }

        public Result setCharCount(long charCount) {
            this.charCount = charCount;
            return this;
        }

        public long getCjkCharCount() {
            return cjkCharCount;
        }

        public Result setCjkCharCount(long cjkCharCount) {
            this.cjkCharCount = cjkCharCount;
            return this;
        }

    }


    public static void main() {
        System.out.println(count("您好Zero哈,zero哈哈Frank"));
    }

}
