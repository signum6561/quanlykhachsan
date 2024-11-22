package com.nhom3_221404;

import java.net.URL;

public class ResourceLoader {

    public static ResourceLoader INSTANCE = new ResourceLoader();

    public URL getResource(String resource) {
        return getClass().getResource(resource);
    }
}
