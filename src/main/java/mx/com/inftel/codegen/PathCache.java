package mx.com.inftel.codegen;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PathCache<X> {

    protected final Root<X> root;
    protected final Map<String, Path<?>> cache;

    public PathCache(Root<X> root) {
        this.root = Objects.requireNonNull(root);
        this.cache = new HashMap<>();
    }

    public Root<X> getRoot() {
        return Objects.requireNonNull(root);
    }

    public Path<?> getPath(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }
        //
        int lastPointIndex = path.lastIndexOf('.');
        if (lastPointIndex == -1) {
            return cache.put(path, root.get(path));
        } else {
            String parent = path.substring(0, lastPointIndex);
            String prop = path.substring(lastPointIndex + 1);
            Path<?> parentPath = getPath(parent);
            return cache.put(path, parentPath.get(prop));
        }
    }

    public <T> Path<Comparable<T>> getComparablePath(String path) {
        @SuppressWarnings("unchecked")
        Path<Comparable<T>> result = (Path<Comparable<T>>) getPath(path);
        return result;
    }

    public <T> Path<T> getTypedPath(String path) {
        @SuppressWarnings("unchecked")
        Path<T> result = (Path<T>) getPath(path);
        return result;
    }
}
