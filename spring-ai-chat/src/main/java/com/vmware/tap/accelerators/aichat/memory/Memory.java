package com.vmware.tap.accelerators.aichat.memory;

import java.util.List;
import java.util.Map;

public interface Memory {

    List<String> getKeys();

    Map<String, Object> load(Map<String, Object> inputs);

    void save(Map<String, Object> inputs, Map<String, Object> outputs);

    void clear();

}
