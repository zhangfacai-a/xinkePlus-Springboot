package com.ruoyi.live.util;

public class LiveLiveNormalizeUtil {

    /** roomName 规范化：trim + 全角空格->半角 + 连续空白压缩 + 小写 */
    public static String normalizeRoomName(String roomName) {
        if (roomName == null) return "";
        String s = roomName.replace('\u3000', ' ').trim();
        s = s.replaceAll("\\s+", " ");
        return s.toLowerCase();
    }
}
