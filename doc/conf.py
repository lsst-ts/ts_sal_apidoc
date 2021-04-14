"""Sphinx configuration file for a Rubin Observatory Telescope and Site package.
This configuration only affects single-package Sphinx documentation builds.
"""
from documenteer.conf.pipelinespkg import *
project = "ts_sal_apidoc"
html_theme_options["logotext"] = project
html_title = project
html_short_title = project
doxylink = {}  # Avoid warning: Could not find tag file _doxygen/doxygen.tag
intersphinx_mapping["ts_sal_apidoc"] = ("https://ts-sal_apidoc.lsst.io", None)
