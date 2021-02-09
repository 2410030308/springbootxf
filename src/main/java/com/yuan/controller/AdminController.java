package com.yuan.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.entity.Admin;
import com.yuan.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TAdmin)表控制层
 *
 * @author makejava
 * @since 2021-01-26 22:53:24
 */
@RestController
@RequestMapping("admin")
public class AdminController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService tAdminService;

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param tAdmin 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Admin> page, Admin tAdmin) {
        return success(this.tAdminService.page(page, new QueryWrapper<>(tAdmin)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tAdminService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tAdmin 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Admin tAdmin) {
        return success(this.tAdminService.save(tAdmin));
    }

    /**
     * 修改数据
     *
     * @param tAdmin 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Admin tAdmin) {
        return success(this.tAdminService.updateById(tAdmin));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tAdminService.removeByIds(idList));
    }
}