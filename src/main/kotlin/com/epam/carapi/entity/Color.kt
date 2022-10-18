package com.epam.carapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "colors")
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val hex: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    val vehicle: Vehicle
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        other as Color

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , hex = $hex )"
    }
}
