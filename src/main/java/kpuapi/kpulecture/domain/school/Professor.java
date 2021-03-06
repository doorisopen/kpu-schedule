package kpuapi.kpulecture.domain.school;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "professor_id")
    private Long id;

    private String professorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Lecture> lectures = new ArrayList<>();

    //==연관관계 메서드==//
    public void setMajor(Major major) {
        this.major = major;
        major.getProfessors().add(this);
    }

    //==생성 메서드==//
    public Professor(String professorName) {
        this.professorName = professorName;
    }

    //==비즈니스 로직==//
    public void changeProfessorName(String professorName) {
        this.setProfessorName(professorName);
    }
}