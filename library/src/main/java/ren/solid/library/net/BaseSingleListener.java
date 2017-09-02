package ren.solid.library.net;

public interface BaseSingleListener<T> {
    /**
     * when data call back success
     *
     * @param data
     */
    void onSuccess(T data);

    /**
     * when data call back error
     *
     * @param e
     */
    void onError(Exception e);

    /**
     * when data call back occurred exception
     *
     * @param e
     */
    void onException(Exception e);
}
