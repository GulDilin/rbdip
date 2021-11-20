package ru.itmo.rbdip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.rbdip.repository.entity.Tag;

public interface TagInterface extends JpaRepository<Tag,Long> {
}
