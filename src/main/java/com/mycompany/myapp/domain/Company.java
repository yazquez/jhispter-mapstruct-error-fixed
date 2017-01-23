package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "fiscal_name", nullable = false)
    private String fiscalName;

    @ManyToOne
    private Country country;

    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> people = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiscalName() {
        return fiscalName;
    }

    public Company fiscalName(String fiscalName) {
        this.fiscalName = fiscalName;
        return this;
    }

    public void setFiscalName(String fiscalName) {
        this.fiscalName = fiscalName;
    }

    public Country getCountry() {
        return country;
    }

    public Company country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Company people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public Company addPerson(Person person) {
        people.add(person);
        person.getCompanies().add(this);
        return this;
    }

    public Company removePerson(Person person) {
        people.remove(person);
        person.getCompanies().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        if (company.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", fiscalName='" + fiscalName + "'" +
            '}';
    }
}
