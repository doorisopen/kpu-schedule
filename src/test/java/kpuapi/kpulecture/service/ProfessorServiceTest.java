package kpuapi.kpulecture.service;

import kpuapi.kpulecture.domain.Major;
import kpuapi.kpulecture.domain.MajorCode;
import kpuapi.kpulecture.domain.Professor;
import kpuapi.kpulecture.repository.ProfessorJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class ProfessorServiceTest {

    @Autowired
    ProfessorJpaRepository professorJpaRepository;
    @Autowired ProfessorService professorService;
    @Autowired MajorService majorService;
    @Autowired
    EntityManager em;

    /**
     * 교수 등록에 성공한다.
     * @throws Exception
     */
    @Test
    public void 교수등록() throws Exception {
        //given
        Major major = new Major(MajorCode.ACS);

        Long majorId = majorService.save(major);

        Professor professor = new Professor();
        professor.setProfessorName("Kim");

        //when
        Long saveId = professorService.join(professor, majorId);

        em.flush();

        //then
        assertEquals(professor, professorJpaRepository.findOne(saveId));
    }

    /**
     * 중복 등록 예외가 발생한다.
     * @throws Exception
     */
    @Test
    public void 중복_등록_예외() throws Exception {
        //given
        Major major = new Major(MajorCode.ACS);

        Long majorId = majorService.save(major);

        Professor professor1 = new Professor();
        professor1.setProfessorName("Kim");

        Professor professor2 = new Professor();
        professor2.setProfessorName("Kim");

        //when
        professorService.join(professor1, majorId);
        professorService.join(professor2, majorId); // 예외 발생

        //then
        fail("예외가 발생해야 하는데...?");
    }

}
