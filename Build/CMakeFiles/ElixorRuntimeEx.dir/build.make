# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.18

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/XFaon/CLionProjects/Elixor

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/XFaon/CLionProjects/Elixor/Build

# Include any dependencies generated for this target.
include CMakeFiles/ElixorRuntimeEx.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/ElixorRuntimeEx.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/ElixorRuntimeEx.dir/flags.make

CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o: CMakeFiles/ElixorRuntimeEx.dir/flags.make
CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o: ../ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/XFaon/CLionProjects/Elixor/Build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o -c /home/XFaon/CLionProjects/Elixor/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx

CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/XFaon/CLionProjects/Elixor/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx > CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.i

CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/XFaon/CLionProjects/Elixor/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx -o CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.s

# Object files for target ElixorRuntimeEx
ElixorRuntimeEx_OBJECTS = \
"CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o"

# External object files for target ElixorRuntimeEx
ElixorRuntimeEx_EXTERNAL_OBJECTS =

ElixorRuntimeEx: CMakeFiles/ElixorRuntimeEx.dir/ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx.o
ElixorRuntimeEx: CMakeFiles/ElixorRuntimeEx.dir/build.make
ElixorRuntimeEx: Externals/KhronosGroup/Vulkan-Hpp/glfw/src/libglfw3.a
ElixorRuntimeEx: /usr/lib/x86_64-linux-gnu/libvulkan.so
ElixorRuntimeEx: /usr/lib/x86_64-linux-gnu/librt.so
ElixorRuntimeEx: /usr/lib/x86_64-linux-gnu/libm.so
ElixorRuntimeEx: CMakeFiles/ElixorRuntimeEx.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/XFaon/CLionProjects/Elixor/Build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable ElixorRuntimeEx"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ElixorRuntimeEx.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/ElixorRuntimeEx.dir/build: ElixorRuntimeEx

.PHONY : CMakeFiles/ElixorRuntimeEx.dir/build

CMakeFiles/ElixorRuntimeEx.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/ElixorRuntimeEx.dir/cmake_clean.cmake
.PHONY : CMakeFiles/ElixorRuntimeEx.dir/clean

CMakeFiles/ElixorRuntimeEx.dir/depend:
	cd /home/XFaon/CLionProjects/Elixor/Build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/XFaon/CLionProjects/Elixor /home/XFaon/CLionProjects/Elixor /home/XFaon/CLionProjects/Elixor/Build /home/XFaon/CLionProjects/Elixor/Build /home/XFaon/CLionProjects/Elixor/Build/CMakeFiles/ElixorRuntimeEx.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/ElixorRuntimeEx.dir/depend

