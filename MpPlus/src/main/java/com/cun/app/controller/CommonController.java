package com.cun.app.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.cun.app.entity.Question;
import com.cun.app.entity.Student;
import com.cun.app.entity.Student2;
import com.cun.app.service.QuestionService;
import com.cun.app.service.StudentService;
import com.cun.app.vo.QuestionStudentVO;
import com.cun.app.vo.RestfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
@EnableSwagger2
public class CommonController {

    @Autowired
    QuestionService questionService;

    @Autowired
    StudentService studentService;

    /*  @RequestMapping(value = "/getStu/{page}/{size}", method = RequestMethod.GET)
      public RestfulResult<Student>  getAllStu(@PathVariable Integer page, @PathVariable Integer size) {
          RestfulResult<Student> restfulResult=new RestfulResult<>();
          Page<Student> stuPage = studentService.selectPage(new Page<>(page, size));
          if (stuPage.getRecords().size() == 0){
             restfulResult.setCode("500");
             restfulResult.setMessage("没有数据");
             restfulResult.setData(null);
          }
          else {
              restfulResult.setCode("200");
              restfulResult.setMessage("success");
              restfulResult.setData(stuPage.getRecords().get(0));
          }
          //return JSON.toJSONString(restfulResult);
          return restfulResult;
      }*/
    @ResponseBody
    @RequestMapping(value = "/getStu/{page}/{size}", method = RequestMethod.GET)
    public Student2 getAllStu( ){
        //RestfulResult<Student> restfulResult = new RestfulResult<>();
        Student2 student2 = new Student2(1, "xiaowang");
        return student2;
    }



    /**
     * {
     *   "code": 200,
     *   "data": {
     *     "total": 10,
     *     "size": 3,
     *     "current": 1,
     *     "records": [
     *       {
     *         "id": 1,
     *         "studentId": 3,
     *         "name": "vv",
     *         "content": "唐代，渝州城里，有一个性格开朗、乐观的小伙子，名叫景天。",
     *         "date": 1534497561000,
     *         "value": 5
     *       },
     *       {
     *         "id": 2,
     *         "studentId": 1,
     *         "name": "cun",
     *         "content": "雪见从小父母双亡，由爷爷唐坤抚养成人。",
     *         "date": 1533201716000,
     *         "value": 20
     *       },
     *       {
     *         "id": 3,
     *         "studentId": 2,
     *         "name": "beibi",
     *         "content": "后来雪见的爷爷唐坤得了消渴之病，须采集蜀山脚下的百花露水才能医治。",
     *         "date": 1533821160000,
     *         "value": 2
     *       }
     *     ],
     *     "pages": 4
     *   }
     * }
     * @param page
     * @param size
     * @return
     *
     */
    @GetMapping("/getAllQuestionByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<Question> questionPage = questionService.selectPage(new Page<>(page, size));
        if (questionPage.getRecords().size() == 0){
            map.put("code",400);
        }
            else {
            map.put("code", 200);
            map.put("data", questionPage);
        }
        return map;
    }
    /**
     *  Time：24 ms - ID：com.cun.app.mapper.QuestionMapper.selectPage
     *  Execute SQL：
     *     SELECT
     *         id AS id,
     *         student_id AS studentId,
     *         content,
     *         `date`,
     *         `value`
     *     FROM
     *         t_question LIMIT 0,
     *         3
     */



    /**
     * {
     *   "code": 200,
     *   "data": {
     *     "total": 10,
     *     "size": 3,
     *     "current": 1,
     *     "records": [
     *       {
     *         "id": 1,
     *         "studentId": 3,
     *         "name": "linhongcun",
     *         "content": "唐代，渝州城里，有一个性格开朗、乐观的小伙子，名叫景天。",
     *         "date": 1534497561000,
     *         "value": 5
     *       },
     *       {
     *         "id": 2,
     *         "studentId": 1,
     *         "name": "linhongcai",
     *         "content": "雪见从小父母双亡，由爷爷唐坤抚养成人。",
     *         "date": 1533201716000,
     *         "value": 20
     *       },
     *       {
     *         "id": 3,
     *         "studentId": 2,
     *         "name": "linyiguang",
     *         "content": "后来雪见的爷爷唐坤得了消渴之病，须采集蜀山脚下的百花露水才能医治。",
     *         "date": 1533821160000,
     *         "value": 2
     *       }
     *     ],
     *     "pages": 4
     *   }
     * }
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAllQuestionWithStudentByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionWithStudentByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<QuestionStudentVO> questionStudent = questionService.getQuestionStudent(new Page<>(page, size));
        if (questionStudent.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionStudent);
        }
        return map;
    }

    @GetMapping("/getAllQuestionWithStudentByPageXML/{page}/{size}")
    public Map<String, Object> getAllQuestionWithStudentByPageXML(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<QuestionStudentVO> questionStudent = questionService.getQuestionStudentXML(new Page<>(page, size));
        if (questionStudent.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionStudent);
        }
        return map;
    }

    /**
     *  Time：35 ms - ID：com.cun.app.mapper.QuestionMapper.getQuestionStudent
     *  Execute SQL：
     *     SELECT
     *         t_question.*,
     *         t_student.`name`
     *     FROM
     *         t_question,
     *         t_student
     *     WHERE
     *         t_question.student_id=t_student.id LIMIT 0,3
     */

}
