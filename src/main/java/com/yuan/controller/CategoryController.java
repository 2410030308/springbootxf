package com.yuan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.entity.Category;
import com.yuan.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TCategory)表控制层
 *
 * @author makejava
 * @since 2021-01-26 22:53:34
 */
@RestController
@RequestMapping("tCategory")
public class CategoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService tCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param tCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Category> page, Category tCategory) {
        return success(this.tCategoryService.page(page, new QueryWrapper<>(tCategory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Category tCategory) {
        return success(this.tCategoryService.save(tCategory));
    }

    /**
     * 修改数据
     *
     * @param tCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Category tCategory) {
        return success(this.tCategoryService.updateById(tCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tCategoryService.removeByIds(idList));
    }
}