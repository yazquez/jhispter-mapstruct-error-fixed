package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> people = new HashSet<>();

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Company> companies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Country people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public Country addPerson(Person person) {
        people.add(person);
        person.setCountry(this);
        return this;
    }

    public Country removePerson(Person person) {
        people.remove(person);
        person.setCountry(null);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public Country companies(Set<Company> companies) {
        this.companies = companies;
        return this;
    }

    public Country addCompany(Company company) {
        companies.add(company);
        company.setCountry(this);
        return this;
    }

    public Country removeCompany(Company company) {
        companies.remove(company);
        company.setCountry(null);
        return this;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        if (country.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
