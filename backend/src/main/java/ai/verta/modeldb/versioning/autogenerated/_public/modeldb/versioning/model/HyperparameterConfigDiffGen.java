// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.generator.java.util.*;
import com.pholser.junit.quickcheck.random.*;
import java.util.*;

public class HyperparameterConfigDiffGen extends Generator<HyperparameterConfigDiff> {
  public HyperparameterConfigDiffGen() {
    super(HyperparameterConfigDiff.class);
  }

  @Override
  public HyperparameterConfigDiff generate(SourceOfRandomness r, GenerationStatus status) {
    // if (r.nextBoolean())
    //     return null;

    HyperparameterConfigDiff obj = new HyperparameterConfigDiff();
    if (r.nextBoolean()) {
      obj.setA(
          Utils.removeEmpty(gen().type(HyperparameterValuesConfigBlob.class).generate(r, status)));
    }
    if (r.nextBoolean()) {
      obj.setB(
          Utils.removeEmpty(gen().type(HyperparameterValuesConfigBlob.class).generate(r, status)));
    }
    if (r.nextBoolean()) {
      obj.setName(Utils.removeEmpty(new StringGenerator().generate(r, status)));
    }
    if (r.nextBoolean()) {
      obj.setStatus(
          Utils.removeEmpty(gen().type(DiffStatusEnumDiffStatus.class).generate(r, status)));
    }
    return obj;
  }
}
