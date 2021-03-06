//package kpuapi.kpulecture.scraping;
//
//import org.springframework.data.annotation.CreatedDate;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WebScraping {
//
//    public static void main(String[] args) {
//        // 강의 구분자 ( A or G )
//        String SCH_ORG_SECT = "G";
//        // 현재 페이지
//        int pageNo = 1;
//        // 페이지 전체 개수
//        int totalPage = getPageCnt(SCH_ORG_SECT);
//
//        while(pageNo <= totalPage) {
//            lectureCrawling(pageNo, SCH_ORG_SECT);
//            pageNo = pageNo + 10;
//        }
//
//    }
//
//    /**
//     * 페이지 개수 구하기
//     * @Return int
//     */
//    public static int getPageCnt(String SCH_ORG_SECT) {
//
//        int totalPage = 0;
//        try {
//            URL url = new URL("http://eclass.kpu.ac.kr/ilos/st/main/course_ing_list.acl?SCH_ORG_SECT="+SCH_ORG_SECT);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
//
//            String line = reader.readLine();
//
//            while((line = reader.readLine()) != null) {
//                if(line.contains("<a href=javascript:listPage('") && line.contains("img")) {
//                    String tmp = line.split("'")[1];
//                    tmp.trim();
//                    totalPage = Integer.parseInt(line.split("'")[1]);
//                }
//            }
//            reader.close();
//
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return totalPage;
//    }
//    /**
//     * 강의 정보 데이터 스크래핑
//     * @Return JSONArray
//     */
//    @SuppressWarnings("unchecked")
//    public static List<CrawlingDto> lectureCrawling(int pageNo, String SCH_ORG_SECT) {
//        List<CrawlingDto> data = new ArrayList<>();
//
//        try {
//            URL url = new URL("http://eclass.kpu.ac.kr/ilos/st/main/course_ing_list.acl?SCH_ORG_SECT="+SCH_ORG_SECT+"&start="+pageNo);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
//            String line = reader.readLine();
//            line.trim();
//
//            String lectureIdx = "";
//            String lectureCode = "";
//            String lectureName = "";
//            String professorName = "";
//            String lectureGubun = "";
//            String lectureYear = "";
//            String lectureSemester = "";
//            String lectureDate = "";
//            String lectureRoom = "";
//
//            while((line = reader.readLine()) != null) {
//
//                if(line.contains("<tr   id=")) {
//                    String code = line.split(">")[0];
//                    code.trim();
//                    code = code.substring(20, 36);
//                    lectureGubun = code.substring(0,1);
//                    lectureCode = code.substring(6,14);
//                } else if(line.contains("<tr class=\"list\"")) {
//                    String code = line.split(">")[0];
//                    code.trim();
//                    code = code.substring(32, 48);
//                    lectureGubun = code.substring(0,1);
//                    lectureCode = code.substring(6,14);
//                }
//
//                if(line.contains("<td class=\"number")) {
//                    lectureIdx = line.split(">")[1].split("<")[0];
//                    lectureIdx.trim();
//                }
//
//                if(line.contains("<td class=\"name") && line.contains("<br>")) {
//                    lectureYear = line.split(">")[1].split("<")[0].substring(0,4);
//                    lectureSemester = line.split(">")[2].split("<")[0];
//                }
//
//                if(line.contains("<td class=\"left") && line.contains("<br>")) {
//                    lectureName = line.split(">")[1].split("<")[0];
//                    lectureName.trim();
//                    professorName = line.split(">")[2].split("<")[0];
//
//                }
//                else if(line.contains("<td class=\"left")) {
//                    lectureDate = line.split(">")[1].split("/td")[0];
//                    if(lectureDate.substring(0,0).equals("<")) {
//                        lectureDate="NULL";
//                        lectureRoom="NULL";
//                    } else {
//                        lectureRoom = lectureDate.substring(lectureDate.indexOf("(")+1,lectureDate.indexOf(")"));
//                        lectureDate = line.split(">")[1].split("</td")[0];
//                        lectureDate = lectureDate.substring(0,lectureDate.indexOf("("));
//                        lectureDate.trim();
//                        lectureRoom.trim();
//                    }
//
//                }
//                if(line.contains("</tr")) {
//                    //db save
//                    CrawlingDto crawlingDto = new CrawlingDto(lectureIdx,
//                            lectureCode,
//                            lectureName,
//                            lectureGubun,
//                            lectureYear,
//                            professorName,
//                            lectureSemester,
//                            lectureDate,
//                            lectureRoom);
//
//                    data.add(crawlingDto);
////                    System.out.print("[" + lectureIdx + " -");
////                    System.out.print(lectureCode+ " -");
////                    System.out.print(lectureName+ " -");
////                    System.out.print(lectureGubun+ " -");
////                    System.out.print(lectureYear+ " -");
////                    System.out.print(professorName+ " -");
////                    System.out.print(lectureSemester+ " -");
////                    System.out.print(lectureDate+ " -");
////                    System.out.print(lectureRoom + "]");
////                    System.out.print("\n");
//                }
//
//            }
//            reader.close();
//
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return data;
//    }
//
//
////    @SuppressWarnings("unchecked")
////    public JSONArray lectureCrawling(JSONArray jsonArray, int pageNo, String SCH_ORG_SECT) {
////        JSONObject lectureInfo = new JSONObject();
////        try {
////            URL url = new URL("http://eclass.kpu.ac.kr/ilos/st/main/course_ing_list.acl?SCH_ORG_SECT="+SCH_ORG_SECT+"&start="+pageNo);
////
////            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
////
////            String line = reader.readLine();
////            line.trim();
////            lectureInfo.clear();
////            while((line = reader.readLine()) != null) {
////                if(line.contains("<tr   id=")) {
////                    lectureInfo = new JSONObject();
////
////                    String code = line.split(">")[0];
////                    code.trim();
////                    code = code.substring(20, 36);
////                    String lectureGubun = code.substring(0,1);
////                    String lectureCode = code.substring(6,14);
////                    lectureInfo.put("code", code);
////                    lectureInfo.put("lectureGubun", lectureGubun);
////                    lectureInfo.put("lectureCode", lectureCode);
////
////                } else if(line.contains("<tr class=\"list\"")) {
////                    lectureInfo = new JSONObject();
////
////                    String code = line.split(">")[0];
////                    code.trim();
////                    code = code.substring(32, 48);
////                    String lectureGubun = code.substring(0,1);
////                    String lectureCode = code.substring(6,14);
////                    lectureInfo.put("code", code);
////                    lectureInfo.put("lectureGubun", lectureGubun);
////                    lectureInfo.put("lectureCode", lectureCode);
////                }
////
////                if(line.contains("<td class=\"number")) {
////                    String lectureIdx = line.split(">")[1].split("<")[0];
////                    lectureIdx.trim();
////
////                    lectureInfo.put("lectureIdx", lectureIdx);
////                }
////
////                if(line.contains("<td class=\"name") && line.contains("<br>")) {
////                    String lectureYear = line.split(">")[1].split("<")[0].substring(0,4);
////                    String lectureSemester = line.split(">")[2].split("<")[0];
////
////                    lectureInfo.put("lectureYear", lectureYear);
////                    lectureInfo.put("lectureSemester", lectureSemester);
////                }
////
////                if(line.contains("<td class=\"left") && line.contains("<br>")) {
////                    String lectureName = line.split(">")[1].split("<")[0];
////                    lectureName.trim();
////
////                    String professorName = line.split(">")[2].split("<")[0];
////
////                    lectureInfo.put("lectureName", lectureName);
////                    lectureInfo.put("professorName", professorName);
////
////                } else if(line.contains("<td class=\"left")) {
////                    String lectureDate = line.split(">")[1].split("/td")[0];
////                    String lectureLocation = "";
////                    if(lectureDate.substring(0).equals("<")) {
////                        lectureDate="NULL";
////                        lectureLocation="NULL";
////                    } else {
////                        lectureLocation = lectureDate.substring(lectureDate.indexOf("(")+1,lectureDate.indexOf(")"));
////                        lectureDate = line.split(">")[1].split("</td")[0];
////                        lectureDate = lectureDate.substring(0,lectureDate.indexOf("("));
////                        lectureDate.trim();
////                        lectureLocation.trim();
////                    }
////                    lectureInfo.put("lectureDate", lectureDate);
////                    lectureInfo.put("lectureLocation", lectureLocation);
////                }
////                if(line.contains("</tr")) {
////                    jsonArray.add(lectureInfo);
////                }
////
////            }
////            reader.close();
////
////        } catch (UnsupportedEncodingException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        } catch (IOException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }
////
////        return jsonArray;
////    }
//
//
//}
