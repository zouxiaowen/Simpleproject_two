package wen.xiao.com.simpleproject.Manager_z;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import wen.xiao.com.simpleproject.entity.ces;
import wen.xiao.com.simpleproject.greendao.cesDao;

/**
 * Created by Administrator on 2017/5/26.
 */

public class MeinvDaoOpe {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, ces stu) {

        DbManager.getDaoSession(context).getCesDao().insert(stu);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<ces> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getCesDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param student
     */
    public static void saveData(Context context, ces student) {
        DbManager.getDaoSession(context).getCesDao().save(student);
    }
    public static void saveData(Context context, List<ces> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getCesDao().saveInTx(list);
    }
    /**
     * 删除数据至数据库
     *
     * @param context
     * @param student 删除具体内容
     */
    public static void deleteData(Context context, ces student) {
        DbManager.getDaoSession(context).getCesDao().delete(student);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getCesDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getCesDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param student
     */
    public static void updateData(Context context, ces student) {
        DbManager.getDaoSession(context).getCesDao().update(student);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ces> queryAll(Context context) {
        QueryBuilder<ces> builder = DbManager.getDaoSession(context).getCesDao().queryBuilder();

        return builder.build().list();
    }

    /**
     * 根据id，其他的字段类似
     *
     * @param context
     * @param id
     * @return
     */
    public static List<ces> queryForId(Context context, long id) {
        QueryBuilder<ces> builder = DbManager.getDaoSession(context).getCesDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder.where(cesDao.Properties.Id.eq(id)).list();
    }
}
