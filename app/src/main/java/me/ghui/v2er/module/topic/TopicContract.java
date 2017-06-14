package me.ghui.v2er.module.topic;

import io.reactivex.Observable;
import me.ghui.v2er.module.base.BaseContract;
import me.ghui.v2er.network.bean.SimpleInfo;
import me.ghui.v2er.network.bean.TopicInfo;

/**
 * Created by ghui on 04/05/2017.
 */

public class TopicContract {
    public interface IView extends BaseContract.IView {
        void fillView(TopicInfo topicInfo, boolean isLoadMore);

        void afterStarTopic();
    }

    public interface IPresenter extends BaseContract.IPresenter {
        void loadData(String topicId, int page);

        void loadData(String topicId);

        Observable<SimpleInfo> doThanks(String replyId, String t);

        void starTopic(String topicId, String t);

//        void blockTopic();

//        void thxCreator();
    }
}