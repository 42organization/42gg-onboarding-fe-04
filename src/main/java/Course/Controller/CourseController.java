package Course.Controller;

import Course.Controller.DTO.req.CourseReqDto;
import Course.Controller.DTO.res.CourseResDto;
import Course.Service.CourseService;
import data.entity.Course;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@PostMapping
	public ResponseEntity<CourseResDto> createCourse(@RequestBody CourseReqDto request) {
		System.out.println(1111111);
		Course course = new Course();
		course.setProfessorName(request.getProfessorName());
		course.setCourseTitle(request.getCourseTitle());
		course.setCurrentCount(request.getCurrentCount());

		CourseResDto response = new CourseResDto("등록성공!");
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseResDto> updateCourse(@PathVariable Integer id, @RequestBody CourseReqDto request) {
		try {
			Course course = courseService.updateCourse(id, request);
			CourseResDto response = new CourseResDto(course, "수정 업데이트 완료");
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new CourseResDto("잘못된 정보입니다."));
		} catch (RuntimeException e) {
			return ResponseEntity.status(409).body(new CourseResDto("중복된 강의가 존재합니다."));
		}
	}

	@PatchMapping("/delete/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Integer id) {
		try {
			courseService.deleteCourse(id);
			return ResponseEntity.ok(new CourseResDto("강의삭제성공!"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(404).body(new CourseResDto("강의를 찾을 수 없습니다"));
		} catch (IllegalStateException e) {
			return ResponseEntity.status(409).body(new CourseResDto("강의가 이미 삭제되었습니다."));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new CourseResDto("강의 삭제 실패!"));
		}
	}

	@PatchMapping("/complete/{id}")
	public ResponseEntity<?> completeCourse(@PathVariable Integer id) {
		try {
			courseService.completeCourse(id);
			return ResponseEntity.ok(new CourseResDto("강의삭제성공!"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(404).body(new CourseResDto("강의를 찾을 수 없습니다"));
		} catch (IllegalStateException e) {
			return ResponseEntity.status(409).body(new CourseResDto("강의가 이미 완료되었습니다."));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new CourseResDto("강의완료등록 실패!"));
		}
	}

}