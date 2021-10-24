package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:08
 */
abstract class CrudServiceImpl<TEntity : BaseEntity, TEditModel : BaseEditModel> protected constructor(
    repository: JpaRepository<TEntity, Long>, modelMapper: EditModelMapper<TEntity, TEditModel>
) : CrudService<TEditModel> {
    protected val repository: JpaRepository<TEntity, Long>
    private val modelMapper: EditModelMapper<TEntity, TEditModel>
    override fun findById(id: Long): TEditModel {
        val entity = repository.getById(id)
        return modelMapper.toModel(entity)
    }

    override fun create(model: TEditModel): Long {
        val newEntity = modelMapper.toNewEntity(model)
        repository.save(newEntity)
        return newEntity.id!!
    }

    override fun update(model: TEditModel) {
        val entity = repository.getById(model.id)
        modelMapper.updateEntity(entity, model)
        repository.save<TEntity>(entity)
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }

    init {
        this.repository = repository
        this.modelMapper = modelMapper
    }
}