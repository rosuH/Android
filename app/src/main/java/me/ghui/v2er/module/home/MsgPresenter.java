package me.ghui.v2er.module.home;

import android.app.Activity;

import me.ghui.v2er.general.Navigator;
import me.ghui.v2er.module.login.LoginActivity;
import me.ghui.v2er.network.APIService;
import me.ghui.v2er.network.GeneralConsumer;
import me.ghui.v2er.network.bean.NotificationInfo;
import me.ghui.v2er.util.UserUtils;
import me.ghui.v2er.widget.dialog.BaseDialog;
import me.ghui.v2er.widget.dialog.ConfirmDialog;

/**
 * Created by ghui on 10/05/2017.
 */

public class MsgPresenter implements MsgContract.IPresenter {

    private MsgContract.IView mView;

    public MsgPresenter(MsgContract.IView view) {
        mView = view;
    }

    @Override
    public void start() {
        if (UserUtils.isLogin()) {
            loadMore(1);
        } else {
            mView.hideLoading();
            new ConfirmDialog.Builder((Activity) mView.getContext())
                    .msg("登录后才能查看消息")
                    .negativeText("取消")
                    .positiveText("去登录", dialog -> Navigator.from(mView.getContext()).to(LoginActivity.class).start())
                    .build()
                    .show();
        }
    }

    @Override
    public void loadMore(int page) {
        APIService.get()
                .notifications(page)
                .compose(mView.rx(page))
                .subscribe(new GeneralConsumer<NotificationInfo>() {
                    @Override
                    public void onConsume(NotificationInfo info) {
                        boolean isLoadMore = page > 1;
                        mView.fillView(info, isLoadMore);
                    }
                });
    }

}
