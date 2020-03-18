// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.*;
import java.util.*;
import java.util.function.Function;

public class HyperparameterConfigDiff implements ProtoType {
  public HyperparameterValuesConfigBlob A;
  public HyperparameterValuesConfigBlob B;
  public String Name;
  public DiffStatusEnumDiffStatus Status;

  public HyperparameterConfigDiff() {
    this.A = null;
    this.B = null;
    this.Name = "";
    this.Status = null;
  }

  public Boolean isEmpty() {
    if (this.A != null && !this.A.equals(null)) {
      return false;
    }
    if (this.B != null && !this.B.equals(null)) {
      return false;
    }
    if (this.Name != null && !this.Name.equals("")) {
      return false;
    }
    if (this.Status != null && !this.Status.equals(null)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": \"HyperparameterConfigDiff\", \"fields\": {");
    boolean first = true;
    if (this.A != null && !this.A.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"A\": " + A);
      first = false;
    }
    if (this.B != null && !this.B.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"B\": " + B);
      first = false;
    }
    if (this.Name != null && !this.Name.equals("")) {
      if (!first) sb.append(", ");
      sb.append("\"Name\": " + "\"" + Name + "\"");
      first = false;
    }
    if (this.Status != null && !this.Status.equals(null)) {
      if (!first) sb.append(", ");
      sb.append("\"Status\": " + Status);
      first = false;
    }
    sb.append("}}");
    return sb.toString();
  }

  // TODO: actually hash
  public String getSHA() {
    StringBuilder sb = new StringBuilder();
    sb.append("HyperparameterConfigDiff");
    if (this.A != null && !this.A.equals(null)) {
      sb.append("::A::").append(A);
    }
    if (this.B != null && !this.B.equals(null)) {
      sb.append("::B::").append(B);
    }
    if (this.Name != null && !this.Name.equals("")) {
      sb.append("::Name::").append(Name);
    }
    if (this.Status != null && !this.Status.equals(null)) {
      sb.append("::Status::").append(Status);
    }

    return sb.toString();
  }

  // TODO: not consider order on lists
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof HyperparameterConfigDiff)) return false;
    HyperparameterConfigDiff other = (HyperparameterConfigDiff) o;

    {
      Function3<HyperparameterValuesConfigBlob, HyperparameterValuesConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.A != null || other.A != null) {
        if (this.A == null && other.A != null) return false;
        if (this.A != null && other.A == null) return false;
        if (!f.apply(this.A, other.A)) return false;
      }
    }
    {
      Function3<HyperparameterValuesConfigBlob, HyperparameterValuesConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.B != null || other.B != null) {
        if (this.B == null && other.B != null) return false;
        if (this.B != null && other.B == null) return false;
        if (!f.apply(this.B, other.B)) return false;
      }
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Name != null || other.Name != null) {
        if (this.Name == null && other.Name != null) return false;
        if (this.Name != null && other.Name == null) return false;
        if (!f.apply(this.Name, other.Name)) return false;
      }
    }
    {
      Function3<DiffStatusEnumDiffStatus, DiffStatusEnumDiffStatus, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.Status != null || other.Status != null) {
        if (this.Status == null && other.Status != null) return false;
        if (this.Status != null && other.Status == null) return false;
        if (!f.apply(this.Status, other.Status)) return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.A, this.B, this.Name, this.Status);
  }

  public HyperparameterConfigDiff setA(HyperparameterValuesConfigBlob value) {
    this.A = Utils.removeEmpty(value);
    return this;
  }

  public HyperparameterConfigDiff setB(HyperparameterValuesConfigBlob value) {
    this.B = Utils.removeEmpty(value);
    return this;
  }

  public HyperparameterConfigDiff setName(String value) {
    this.Name = Utils.removeEmpty(value);
    return this;
  }

  public HyperparameterConfigDiff setStatus(DiffStatusEnumDiffStatus value) {
    this.Status = Utils.removeEmpty(value);
    return this;
  }

  public static HyperparameterConfigDiff fromProto(
      ai.verta.modeldb.versioning.HyperparameterConfigDiff blob) {
    if (blob == null) {
      return null;
    }

    HyperparameterConfigDiff obj = new HyperparameterConfigDiff();
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, HyperparameterValuesConfigBlob>
          f = x -> HyperparameterValuesConfigBlob.fromProto(blob.getA());
      obj.A = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, HyperparameterValuesConfigBlob>
          f = x -> HyperparameterValuesConfigBlob.fromProto(blob.getB());
      obj.B = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, String> f =
          x -> (blob.getName());
      obj.Name = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, DiffStatusEnumDiffStatus> f =
          x -> DiffStatusEnumDiffStatus.fromProto(blob.getStatus());
      obj.Status = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder toProto() {
    ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder builder =
        ai.verta.modeldb.versioning.HyperparameterConfigDiff.newBuilder();
    {
      if (this.A != null && !this.A.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setA(this.A.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.B != null && !this.B.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setB(this.B.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Name != null && !this.Name.equals("")) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setName(this.Name);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Status != null && !this.Status.equals(null)) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setStatus(this.Status.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitHyperparameterConfigDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepHyperparameterValuesConfigBlob(this.A);
    visitor.preVisitDeepHyperparameterValuesConfigBlob(this.B);
    visitor.preVisitDeepString(this.Name);
    visitor.preVisitDeepDiffStatusEnumDiffStatus(this.Status);
  }

  public HyperparameterConfigDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitHyperparameterConfigDiff(this);
  }

  public HyperparameterConfigDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.setA(visitor.postVisitDeepHyperparameterValuesConfigBlob(this.A));
    this.setB(visitor.postVisitDeepHyperparameterValuesConfigBlob(this.B));
    this.setName(visitor.postVisitDeepString(this.Name));
    this.setStatus(visitor.postVisitDeepDiffStatusEnumDiffStatus(this.Status));
    return this.postVisitShallow(visitor);
  }
}
