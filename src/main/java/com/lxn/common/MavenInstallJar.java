package com.lxn.common;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 批量生成maven安装指令和pom.xml内容
 */
public class MavenInstallJar {
    private String groupId = "my.lib";
//    private String prefix_jar = "mylib-";

    private void outputInstallJarBatchFile(String dir) {
        File d = new File(dir);
        File[] fs = d.listFiles();
        for(File f : fs) {
            String name = f.getName().toLowerCase();
            if(f.isFile() && name.endsWith(".jar")) {
                name = name.replaceAll(".jar", "");
                String[] ns = name.split("-");
                String version = "1.0";
                StringBuffer sb = new StringBuffer();
                for(String n : ns) {
                    if(n.length()>0 && isInteger(n.substring(0, 1))) {
                        version = n;
                    } else {
                        sb.append(n).append("-");
                    }
                }
                if(sb.toString().endsWith("-"))
                    sb.deleteCharAt(sb.length()-1);
                String artifactId = sb.toString();
//                if(Util.o2s(prefix_jar).length()>0)
//                    artifactId = prefix_jar + artifactId;
                sb.setLength(0);
                sb.append("mvn install:install-file -DgroupId=").append(groupId);
                sb.append(" -DartifactId=").append(artifactId);
                sb.append(" -Dversion=").append(version);
                sb.append(" -Dfile=").append(f.getAbsolutePath());
                sb.append(" -Dpackaging=jar -DgeneratePom=true");
                System.out.println(sb.toString());
            } else if (f.isDirectory()) {
                outputInstallJarBatchFile(f.getAbsolutePath());
            }
        }
    }

    private void printDependency(String dir) {
        File d = new File(dir);
        File[] fs = d.listFiles();
        for(File f : fs) {
            String name = f.getName().toLowerCase();
            if(f.isFile() && name.endsWith(".jar")) {
                name = name.replaceAll(".jar", "");
                String[] ns = name.split("-");
                String version = "1.0";
                StringBuffer sb = new StringBuffer();
                for(String n : ns) {
                    if(n.length()>0 && isInteger(n.substring(0, 1))) {
                        version = n;
                    } else {
                        sb.append(n).append("-");
                    }
                }
                if(sb.toString().endsWith("-"))
                    sb.deleteCharAt(sb.length()-1);
                String artifactId = sb.toString();
                sb.setLength(0);
                sb.append("<dependency>\n");
                sb.append("  <groupId>").append(groupId).append("</groupId>\n");
                sb.append("  <artifactId>").append(artifactId).append("</artifactId>\n");
                sb.append("  <version>").append(version).append("</version>\n");
                sb.append("</dependency>");
                System.out.println(sb.toString());
            }else if (f.isDirectory()) {
                printDependency(f.getAbsolutePath());
            }
        }
    }

    /*方法二：推荐，速度最快
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        String dirs = "/Users/liangxuning/Private/git_project/gframe-element/lib,/Users/liangxuning/Private/git_project/gframe-element/base_lib";
        String dirss = "/Users/liangxuning/Private/IdeaProjects/ElementPackingTool/lib";
        MavenInstallJar inst = new MavenInstallJar();
//        for (String dir : dirs.split(",")) {
//            inst.outputInstallJarBatchFile(dir);
//            inst.printDependency(dir);
//        }
//        inst.outputInstallJarBatchFile(dirss);
            inst.printDependency(dirss);

    }
}
