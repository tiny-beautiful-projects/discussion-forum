package com.example.discussion_forum.repository;

import com.example.discussion_forum.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
