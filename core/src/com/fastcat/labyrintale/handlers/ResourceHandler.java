package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.handlers.resourcetype.MultipleResourceRequest;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ResourceHandler {
    @Setter
    @Getter
    private boolean started;

    private final Queue<ResourceRequest<?>> queuedRequests;

    private final AssetManager assetManager;

    public ResourceHandler(AssetManager assetManager) {
        this.assetManager = assetManager;
        queuedRequests = new Queue<>();
        started = false;
    }

    /***
     * Request {@link AssetManager} to load that resource
     * <br>
     * @param resourceRequest resource to request
     * @param <R> expected return type of resource
     */
    public <R> void requestResource(ResourceRequest<R> resourceRequest) {
        if (resourceRequest instanceof MultipleResourceRequest) {
            MultipleResourceRequest<R> m = (MultipleResourceRequest<R>) resourceRequest;
            for (String value : m.getResourceNames().values()) {
                assetManager.load(value, resourceRequest.getResourceType());
                queuedRequests.addLast(resourceRequest);
            }
            return;
        }
        assetManager.load(resourceRequest.getResourceName(), resourceRequest.getResourceType());
        queuedRequests.addLast(resourceRequest);
    }

    /***
     * Must be called by main {@link ApplicationListener#render()}
     */
    public boolean process() {
        if (assetManager.update(17)) {
            System.out.println("Called process");
            boolean actualProcessing = false;
            while (queuedRequests.notEmpty()) {
                actualProcessing = true;
                ResourceRequest<?> req = queuedRequests.removeFirst();

                if (req instanceof MultipleResourceRequest<?>) {
                    MultipleResourceRequest<?> multipleReq = (MultipleResourceRequest<?>) req;

                    for (Map.Entry<String, String> resourceName :
                            multipleReq.getResourceNames().entrySet()) {
                        Object loadedResource = assetManager.get(resourceName.getValue(), req.getResourceType());
                        req.getCallback().onResourceLoaded(loadedResource, resourceName.getKey());
                    }
                    continue;
                }
                Object loadedResource = assetManager.get(req.getResourceName(), req.getResourceType());
                req.getCallback().onResourceLoaded(loadedResource, req.getArgs());
            }
            assetManager.finishLoading();
            return actualProcessing;
        }
        return false;
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public interface ResourceCallback {
        /***
         * Called when {@link AssetManager} completed loading all resources
         * @param resource
         */
        void onResourceLoaded(Object resource, Object... args);
    }

    public static class ResourceRequest<R> {

        @Setter
        @NotNull
        @Getter
        private String resourceName;

        @NotNull
        @Getter
        private final Class<R> resourceType;

        @NotNull
        @Getter
        private final ResourceCallback callback;

        @Nullable
        @Setter
        @Getter
        private Object[] args;

        public ResourceRequest(String resourceName, Class<R> resourceType, ResourceCallback callback, Object... args) {
            this.resourceName = resourceName;
            this.resourceType = resourceType;
            this.callback = callback;
            this.args = args;
        }
    }
}
