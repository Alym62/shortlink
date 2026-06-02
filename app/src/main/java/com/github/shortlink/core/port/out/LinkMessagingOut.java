package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.Link;

public interface LinkMessagingOut {
    void publishUpdateLinkCount(Link link);
}
