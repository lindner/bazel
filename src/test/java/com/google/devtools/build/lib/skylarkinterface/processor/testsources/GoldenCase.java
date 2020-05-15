// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.skylarkinterface.processor.testsources;

import com.google.devtools.build.lib.skylarkinterface.Param;
import com.google.devtools.build.lib.skylarkinterface.StarlarkMethod;
import com.google.devtools.build.lib.syntax.Dict;
import com.google.devtools.build.lib.syntax.Sequence;
import com.google.devtools.build.lib.syntax.StarlarkSemantics;
import com.google.devtools.build.lib.syntax.StarlarkThread;
import com.google.devtools.build.lib.syntax.StarlarkValue;

/** Test source file verifying various proper uses of StarlarkMethod. */
public class GoldenCase implements StarlarkValue {

  @StarlarkMethod(name = "struct_field_method", documented = false, structField = true)
  public String structFieldMethod() {
    return "foo";
  }

  @StarlarkMethod(name = "zero_arg_method", documented = false)
  public Integer zeroArgMethod() {
    return 0;
  }

  @StarlarkMethod(
      name = "zero_arg_method_with_thread",
      documented = false,
      useStarlarkThread = true)
  public Integer zeroArgMethodWithThread(StarlarkThread thread) {
    return 0;
  }

  @StarlarkMethod(
      name = "three_arg_method",
      documented = false,
      parameters = {
        @Param(name = "one", type = String.class, named = true),
        @Param(name = "two", type = Integer.class, named = true),
        @Param(
            name = "three",
            type = String.class,
            named = true,
            defaultValue = "None",
            noneable = true),
      })
  public String threeArgMethod(String one, Integer two, Object three) {
    return "bar";
  }

  @StarlarkMethod(
      name = "three_arg_method_with_params_and_thread",
      documented = false,
      parameters = {
        @Param(name = "one", type = String.class, named = true),
        @Param(name = "two", type = Integer.class, named = true),
        @Param(name = "three", type = String.class, named = true),
      },
      useStarlarkThread = true)
  public String threeArgMethodWithParams(
      String one, Integer two, String three, StarlarkThread thread) {
    return "baz";
  }

  @StarlarkMethod(
      name = "many_arg_method_mixing_positional_and_named",
      documented = false,
      parameters = {
        @Param(name = "one", type = String.class, positional = true, named = false),
        @Param(name = "two", type = String.class, positional = true, named = true),
        @Param(
            name = "three",
            type = String.class,
            positional = true,
            named = true,
            defaultValue = "three"),
        @Param(name = "four", type = String.class, positional = false, named = true),
        @Param(
            name = "five",
            type = String.class,
            positional = false,
            named = true,
            defaultValue = "five"),
        @Param(name = "six", type = String.class, positional = false, named = true),
      })
  public String manyArgMethodMixingPositionalAndNamed(
      String one, String two, String three, String four, String five, String six) {
    return "baz";
  }

  @StarlarkMethod(
      name = "two_arg_method_with_params_and_thread_and_kwargs",
      documented = false,
      parameters = {
        @Param(name = "one", type = String.class, named = true),
        @Param(name = "two", type = Integer.class, named = true),
      },
      extraKeywords = @Param(name = "kwargs"),
      useStarlarkThread = true)
  public String twoArgMethodWithParamsAndInfoAndKwargs(
      String one, Integer two, Dict<String, Object> kwargs, StarlarkThread thread) {
    return "blep";
  }

  @StarlarkMethod(
      name = "two_arg_method_with_env_and_args_and_kwargs",
      documented = false,
      parameters = {
        @Param(name = "one", type = String.class, named = true),
        @Param(name = "two", type = Integer.class, named = true),
      },
      extraPositionals = @Param(name = "args"),
      extraKeywords = @Param(name = "kwargs"),
      useStarlarkThread = true)
  public String twoArgMethodWithParamsAndInfoAndKwargs(
      String one, Integer two, Sequence<?> args, Dict<?, ?> kwargs, StarlarkThread thread) {
    return "yar";
  }

  @StarlarkMethod(
      name = "selfCallMethod",
      selfCall = true,
      parameters = {
        @Param(name = "one", type = String.class, named = true),
        @Param(name = "two", type = Integer.class, named = true),
      },
      documented = false)
  public Integer selfCallMethod(String one, Integer two) {
    return 0;
  }

  @StarlarkMethod(
      name = "struct_field_method_with_semantics",
      documented = false,
      structField = true,
      useStarlarkSemantics = true)
  public String structFieldMethodWithSemantics(StarlarkSemantics starlarkSemantics) {
    return "dragon";
  }

  @StarlarkMethod(
      name = "method_with_list_and_dict",
      documented = false,
      parameters = {
        @Param(name = "one", type = Sequence.class, named = true),
        @Param(name = "two", type = Dict.class, named = true),
      })
  public String methodWithListandDict(Sequence<?> one, Dict<?, ?> two) {
    return "bar";
  }
}
