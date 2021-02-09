package com.yuan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.entity.Orderitem;
import com.yuan.service.OrderitemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TOrderitem)表控制层
 *
 * @author makejava
 * @since 2021-01-26 22:53:35
 */
@RestController
@RequestMapping("tOrderitem")
public class OrderitemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrderitemService tOrderitemService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param tOrderitem 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Orderitem> page, Orderitem tOrderitem) {
        return success(this.tOrderitemService.page(page, new QueryWrapper<>(tOrderitem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tOrderitemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tOrderitem 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Orderitem tOrderitem) {
        return success(this.tOrderitemService.save(tOrderitem));
    }

    /**
     * 修改数据
     *
     * @param tOrderitem 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Orderitem tOrderitem) {
        return success(this.tOrderitemService.updateById(tOrderitem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tOrderitemService.removeByIds(idList));
    }
}