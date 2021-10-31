package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.Vod
import net.ukisoft.ukistreams.model.core.EditModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.09.2020 00:31
 */
class VodEditModelMapper(private val vodPartRepository: VodPartRepository) : EditModelMapper<Vod, VodEditModel> {
    private val partMapper: VodPartEditModelMapper = VodPartEditModelMapper()

    override fun toModel(entity: Vod): VodEditModel = VodEditModel(
        entity.id!!,
        entity.type!!,
        entity.parts
            .map(partMapper::toModel)
            .sortedBy { it.ordinal }
    )

    override fun toNewEntity(model: VodEditModel): Vod {
        val entity = Vod()
        updateEntity(entity, model)
        return entity
    }

    override fun updateEntity(entity: Vod, model: VodEditModel) {
        entity.type = model.type
        val partModelMap = model.parts
            .filter { it.id != null }
            .associateBy { it.id!! }

        entity.parts
            .filter { !partModelMap.containsKey(it.id) }
            .forEach(entity::removePart)

        model.parts
            .filter { x: VodPartEditModel -> x.id == null }
            .forEach { partModel ->
                val partEntity = partMapper.toNewEntity(partModel)
                entity.addPart(partEntity)
                vodPartRepository.save(partEntity)
            }
        entity.parts
            .filter { partModelMap.containsKey(it.id) }
            .forEach { partEntity ->
                val partModel = partModelMap[partEntity.id]
                partMapper.updateEntity(partEntity, partModel!!)
                vodPartRepository.save(partEntity)
            }
    }
}