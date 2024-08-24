package com.embarkx.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// this would also work, but JpaRepository extends from CrudRepository with more functionalities
/*public interface CrudJobRepository extends CrudRepository<Job, Long> {
}*/

// <Entity class, and primary key type>
public interface JobRepository extends JpaRepository<Job, Long> {
}
