package library.permission

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PermissionRepository extends CrudRepository<Permission, Long> {

    @Query('''SELECT permission
                FROM User as user
                JOIN user.roles as role
                JOIN role.permissions as permission
                WHERE user.id = ?1
                AND user.isDeleted = false
                AND role.isDeleted = false
                AND permission.isDeleted = false
                ORDER BY permission.name ASC''')
    Iterable<Permission> findAllByUserId(Long id)

}
