package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.model.core.BaseEditModel
import net.ukisoft.ukistreams.model.core.BaseItemModel
import net.ukisoft.ukistreams.model.core.CrudService
import net.ukisoft.ukistreams.model.core.FindAllService
import net.ukisoft.ukistreams.entities.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import net.ukisoft.ukistreams.model.core.EditModelMapper
import net.ukisoft.ukistreams.model.core.CrudServiceImpl
import java.util.stream.Collectors

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:26
 */
interface CrudAllService<TEditModel : BaseEditModel, TItemModel : BaseItemModel> : CrudService<TEditModel>,
    FindAllService<TItemModel>