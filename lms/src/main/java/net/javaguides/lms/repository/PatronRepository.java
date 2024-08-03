package net.javaguides.lms.repository;

import net.javaguides.lms.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron,Long> {
}
