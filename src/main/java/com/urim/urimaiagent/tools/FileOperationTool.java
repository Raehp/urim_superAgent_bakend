package com.urim.urimaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.urim.urimaiagent.constants.FileConstants;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * 文件操作工具类 (提供文件读写功能)
 */
public class FileOperationTool {

    private final String FILE_DIR = FileConstants.FILE_SAVE_DIR + "/file";

    /**
     * 读取文件
     *
     * @param fileName 文件名
     */
    @Tool(description = "Read content from a file")
    public String readFile(@ToolParam(description = "Name of a file to read") String fileName) {
        String filePath = FILE_DIR + "/" + fileName;
        try {
          return FileUtil.readUtf8String(filePath);
        } catch (Exception e) {
            return "Error reading file" + e.getMessage();
        }
    }

    /**
     * 写入文件
     *
     * @param fileName 文件名
     * @param content  文件内容
     */
    @Tool(description = "Write content to a file")
    public String writeFile(@ToolParam(description = "Name of the file to write") String fileName,
                            @ToolParam(description = "Content to write to the file") String content) {
        String filePath = FILE_DIR + "/" + fileName;
        try {
            FileUtil.mkdir(FILE_DIR);
            FileUtil.writeUtf8String(content, filePath);
            return "File written successfully to" + filePath;
        } catch (Exception e) {
            return "Error writing to file" + e.getMessage();
        }
    }
}
